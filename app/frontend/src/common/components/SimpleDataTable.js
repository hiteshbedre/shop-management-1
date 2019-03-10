import React from 'react';
const uuid = require('uuid/v1');

const SimpleDataTable = props => {
  const { headers, data } = props;
  if (data && data.length > 0) {
    return (
      <table>
        <thead>
        {renderHeader(headers)}
        </thead>
        <tbody>
        {data.map(renderRow)}
        </tbody>
      </table>
    );
  }
  return null;
};

const renderHeader = data => (
  <tr key={uuid()}>
    {data.map(t => <th key={uuid()}>{t}</th>)}
  </tr>
);

const renderRow = data => (
  <tr key={uuid()}>
    {data.map(t => <td key={uuid()}>{t}</td>)}
  </tr>
);

export default SimpleDataTable;