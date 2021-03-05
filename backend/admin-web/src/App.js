// https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI
import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "./App.css";
import { styles } from "./css-common"

import AddUser from "./components/add-user.component";
import User from "./components/user.component";
import UsersList from "./components/users-list.component";

import { AppBar, Toolbar, Typography, withStyles } from '@material-ui/core';

class App extends Component {
  render() {
    const { classes } = this.props

    return (
      <div>
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
          </Toolbar>
        </AppBar>

          <Switch>
            <Route exact path={["/", "/users"]} component={UsersList} />
            <Route exact path="/add" component={AddUser} />
            <Route path="/users/:id" component={User} />
          </Switch>
      </div>
    );
  }
}

export default withStyles(styles)(App);

