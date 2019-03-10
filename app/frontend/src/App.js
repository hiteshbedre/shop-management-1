import React, { Component } from 'react';
import CsvFileUploader from './components/common/CsvFileUploader';
import SimpleDataTable from './components/common/SimpleDataTable';
import './App.css';

const axios = require('axios');

export default class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      headers: ['Shop Number', 'Start Date', 'End Date'],
      data: null,
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
      <div className="app">
        <h1>Shop Management</h1>
        <h2>Import Merchant's Shops</h2>
        <CsvFileUploader onRead={this.uploadCsvContent.bind(this)}/>
        {this.renderContent()}
      </div>
    );
  }

  renderContent() {
    if (this.state.errors.length > 0) {
      return (
        <div className="error">
          <h2>Invalid data! Please obey the following data guidelines:</h2>
          <ol>
            {this.state.errors.map(error => <li>{error}</li>)}
          </ol>
        </div>
      );
    }
    else if (this.state.data !== null) {
      return (
        <div className="data">
          <h2>Merchant's Shops</h2>
          <label for="date">Find Active Shops</label>
          <input id="date" type="date" onChange={e => this.fetchShops(e.target.value)}/>
          <SimpleDataTable headers={this.state.headers} data={this.state.data}/>
        </div>
      );
    }
  }
}