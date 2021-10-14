import React from 'react';

import CanvasJSReact from '../CanvasJsChart/canvasjs.react';
import { CanvasJS, CanvasJSChart } from '../CanvasJsChart/canvasjs.react';

const Chart = ({ data, id }) => {

    let options = {
        animationEnabled: true,
        theme: "dark2",
        title: {
            text: ""
        },
        axisY: {
            title: "Minutes",
            scaleBreaks: {
                autoCalculate: true,
                type: "wavy",
                lineColor: "white"
            }
        },
        data: [{
            type: "column",
            indexLabel: "{y}",
            indexLabelFontColor: "white",
        }]
    }

    const formattedOptions = () => {
        console.log("props", data.values, data.date);
        options.data[0].dataPoints = data.values;
        options.title = { text: data.date }

        console.log("options", options);
        return options;
    }

    return <div>
        <CanvasJSChart key={id} options={formattedOptions()} />
    </div>
}

export default Chart;