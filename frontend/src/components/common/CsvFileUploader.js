import React from 'react';

const CsvFileUploader = props => {
  return (
    <div>
      <input type="file" accept=".csv" onChange={e => readCsvFile(props, e.target.files[0])} />
    </div>
  );
};

const readCsvFile = (props, file) => {
  const fileReader = new FileReader();
  fileReader.onloadend = e => fileReader.result !== null && props.onRead && props.onRead(fileReader.result);
  fileReader.readAsText(file);
};

export default CsvFileUploader;