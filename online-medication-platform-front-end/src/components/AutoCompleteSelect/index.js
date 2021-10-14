import React, { useEffect, useState } from 'react';

import AutoCompleteSelect from './AutoCompleteSelect';

const AutoCompleteFormatter = ({ options, field, selectedOption, setSelectedOption, placeholder }) => {

    const [formattedOptions, setFormattedOptions] = useState([]);

    useEffect(() => {
        let formattedOptions = [];
        if (options != null && options.length > 0) {
            options.map((item, index) => {
                formattedOptions.push({ data: item, value: item[field], label: item[field] });
            });
        }

        setFormattedOptions(formattedOptions);
    }, [options]);

    return <AutoCompleteSelect
        options={formattedOptions}
        selectedOption={selectedOption}
        setSelectedOption={setSelectedOption}
        placeholder={placeholder} />
}

export default AutoCompleteFormatter;