import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import { withStyles } from '@material-ui/styles';

const styles = (theme) => ({
    root: {
        display: 'flex',
    },
    formControl: {
        // margin: 5,
        fullWidth: true,
        display: "flex",
    },
    selectEmpty: {
        marginTop: 5,
        width: 600
    },
});

const Selector = ({ classes, options, value, setValue }) => {

    const handleChange = (event) => {
        setValue(event.target.value);
    };

    return <form className={classes.root} autoComplete="off">
        <FormControl className={classes.formControl}>
            <Select
                value={value}
                onChange={handleChange}
                displayEmpty
                className={classes.selectEmpty}
                variant="outlined"
            >
                {options.map((item, index) => <MenuItem value={index}>{item}</MenuItem>)}
            </Select>
            <FormHelperText>choose the item you want to add</FormHelperText>
        </FormControl>
    </form>
}

export default withStyles(styles)(Selector);