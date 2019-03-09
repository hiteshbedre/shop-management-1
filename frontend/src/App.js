import React, { Component } from 'react';
import CsvFileUploader from './components/common/CsvFileUploader';
import './App.css';

const axios = require('axios');

class App extends Component {

  constructor(props) {
    super(props);

    this.uploadCsvContent = this.uploadCsvContent.bind(this);
  }

  uploadCsvContent(content) {
    axios.post('/import-shops', content, {
      headers: {'Content-Type': 'text/csv'}
    });
  }

  render() {
    return (
      <div>
        <CsvFileUploader onRead={this.uploadCsvContent}/>
      </div>
    );
  }
}

export default App;