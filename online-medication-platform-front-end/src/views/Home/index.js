import React, { Fragment } from 'react';
import { Typography, Container, Button, withStyles } from '@material-ui/core';

const styles = (theme) => ({
    submit: {
        marginTop: 15,
        marginBottom: 15,
    },
});

const Home = ({ classes }) => {
    return <Fragment>
        <Typography variant="h2" align="center" style={{ margin: 30 }}>
            Welcome to the online medication platform
        </Typography>
        <Container component="main" maxWidth="xs">
            <Button
                fullWidth
                variant="contained"
                color="primary"
                className={classes.submit}
                href="/log-in"
            >
                Log In
          </Button>
        </Container>
    </Fragment>
}

export default withStyles(styles)(Home);