import React, { Component } from 'react';

export default class CsvFileUploader extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedFile: {}
    }
  }

  render() {
    return (
      <div>
        <input type="file" accept=".csv" onChange={e => this.setState({selectedFile: e.target.files[0]})}/>
        <button onClick={this.extractCsvFile.bind(this)}>Upload</button>
      </div>
    );
  }

  extractCsvFile() {
    if (this.state.selectedFile != null && this.props.onRead) {
      const fileReader = new FileReader();
      fileReader.onloadend = e => fileReader.result !== null && this.props.onRead(fileReader.result);
      fileReader.readAsText(this.state.selectedFile);
    }
  }
}