import React, { Component } from 'react';
import CsvFileUploader from './components/common/CsvFileUploader';
import SimpleDataTable from './components/common/SimpleDataTable';
import './App.css';

const axios = require('axios');

class App extends Component {

  constructor(props) {
    super(props);

    this.uploadCsvContent = this.uploadCsvContent.bind(this);
    this.state = {
      headers: ['Shop Number', 'Start Date', 'End Date'],
      data: [],
      errors: []
    };
  }

  uploadCsvContent(content) {
    axios.post('/import-shops', content, {
      headers: {'Content-Type': 'text/csv'}
    })
      .then(response => this.setState(
        {
          data: response.data.map(Object.values),
          errors: []
        }
      ))
      .catch(({response: {data: response}}) => {
        if (response.status === 500) {
          this.setState({errors: [response.message]});
        }
        else if (response.status === 400) {
          const errors = Array.from(new Set(response.errors.map(t => t.defaultMessage)));
          this.setState({errors});
        }
      });

  }

  fetchShops(date) {
    axios.get(`/fetch-shops?date=${date}`)
      .then(response => this.setState({ data: response.data.map(Object.values) }));
  }

  render() {
    return (
      <div>
        <h1>Shop Management</h1>
        <h2>Import Merchant's Shops</h2>
        <CsvFileUploader onRead={this.uploadCsvContent}/>
        {this.renderErrors()}
        <h2>Merchant's Shops</h2>
        <input type="date" onChange={e => this.fetchShops(e.target.value)}/>
        <SimpleDataTable headers={this.state.headers} data={this.state.data}/>
      </div>
    );
  }

  renderErrors() {
    if (this.state.errors) {
      return (
        <div className="error">
          <h3>Violations</h3>
          {this.state.errors.map(error => <p>{error}</p>)}
        </div>
      );
    }
    return null;
  }
}

export default App;