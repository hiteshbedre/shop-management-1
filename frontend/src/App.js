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
      data: []
    };
  }

  uploadCsvContent(content) {
    axios.post('/import-shops', content, {
      headers: {'Content-Type': 'text/csv'}
    }).then(response => this.setState({ data: response.data.map(Object.values) }));
  }

  render() {
    return (
      <div>
        <CsvFileUploader onRead={this.uploadCsvContent} />
        <SimpleDataTable headers={this.state.headers} data={this.state.data} />
      </div>
    );
  }
}

export default App;