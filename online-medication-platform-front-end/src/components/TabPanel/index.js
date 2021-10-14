import React from 'react';
import Typography from '@material-ui/core/Typography';
import { Box } from '@material-ui/core';

const TabPanel = ({ children, value, index }) => {
    return <Typography
        component="div"
        role="tabpanel"
        hidden={value !== index}
        id={`full-width-tabpanel-${index}`}
        aria-labelledby={`full-width-tab-${index}`}
    >
        <Box p={3}>{children}</Box>
    </Typography>
}

export default TabPanel;