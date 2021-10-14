import React from 'react';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';

const TabsHolder = ({ options, value, handleTabChange }) => {
    return <div>
        <AppBar position="static" color="default">
            <Tabs
                value={value}
                onChange={handleTabChange}
                indicatorColor="primary"
                textColor="primary"
            >
                {options.map((item) => <Tab label={item} />)}
            </Tabs>
        </AppBar>
    </div>
}

export default TabsHolder;
