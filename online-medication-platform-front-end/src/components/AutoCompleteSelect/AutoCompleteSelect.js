import React, { useState, useEffect } from 'react';
import Select from 'react-select';

// const options = [
//     { value: 'chocolate', label: 'Chocolate', data: { success: "true" } },
//     { value: 'strawberry', label: 'Strawberry', data: { success: "false" } },
//     { value: 'vanilla', label: 'Vanilla', data: { success: "maybe" } },
// ];

const AutoCompleteSelect = ({ options, selectedOption, setSelectedOption, placeholder }) => {

    const handleChange = (selectedOption) => {
        setSelectedOption(selectedOption);
    };

    return <Select
        value={selectedOption}
        onChange={handleChange}
        options={options}
        placeholder={placeholder}
    />
}

export default AutoCompleteSelect;