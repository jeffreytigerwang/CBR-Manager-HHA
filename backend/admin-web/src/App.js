// https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI
import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "./App.css";
import { styles } from "./css-common"

import AddUser from "./components/add-user.component";
import User from "./components/user.component";
import UsersList from "./components/users-list.component";
import Statistics from "./components/statistics.component"

import { AppBar, Toolbar, Typography, withStyles, Grid, Paper } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

class App extends Component {
  render() {
    const { classes } = this.props

    return (
      <Grid className={classes.root} container direction="column" style={{backgroundColor: "#f5f5f5"}}>
        <Grid item>

        <AppBar className={classes.appBar} position="static">
          <Toolbar>
            <Typography className={classes.name} variant="h6">
              Admin Page
            </Typography>
            <Link to={"/users"} className={classes.link}>
              <Typography variant="body2">
                Users
              </Typography>
            </Link>
            <Link to={"/add"} className={classes.link}>
              <Typography variant="body2">
                Add
            </Typography>
            </Link>
            <Link to={"/statistics"} className={classes.link}>
              <Typography variant="body2">
                Statistics
              </Typography>
            </Link>
          </Toolbar>
        </AppBar>
        </Grid>

        <Grid item container spacing={3}>
          <Grid item xs={1} sm={1} />
          <Grid item xs={12} sm={10}>
          <Switch>
              <Route exact path={["/", "/users"]} component={UsersList} />
              <Route exact path="/add" component={AddUser} />
              <Route path="/users/:id" component={User} />
              <Route path="/statistics" component={Statistics} />
          </Switch>
          </Grid>
          <Grid item xs={1} sm={1} />
        </Grid>

      </Grid>
    );
  }
}

export default withStyles(styles)(App);

