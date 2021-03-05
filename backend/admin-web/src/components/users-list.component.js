//https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI
import React, { Component } from "react";
import UserDataService from "../services/user.service";
import { Link } from "react-router-dom";

import { styles } from "../css-common"
import { TextField, Button, Grid, ListItem, withStyles } from "@material-ui/core";

class UsersList extends Component {
  constructor(props) {
    super(props);
    /*
    this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
    this.retrieveTutorials = this.retrieveTutorials.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveTutorial = this.setActiveTutorial.bind(this);
    this.removeAllTutorials = this.removeAllTutorials.bind(this);
    this.searchTitle = this.searchTitle.bind(this);
    */

    this.onChangeSearchName = this.onChangeSearchName.bind(this);
    this.retrieveUsers = this.retrieveUsers.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveUser = this.setActiveUser.bind(this);
    this.removeAllUsers = this.removeAllUsers.bind(this);
    this.searchName = this.searchName.bind(this);

    this.state = {
      users: [],
      currentUser: null,
      currentIndex: -1,
      searchName: ""
    };
  }

  componentDidMount() {
    this.retrieveUsers();
  }

  searchName() {
    UserDataService.findByName(this.state.searchName)
      .then(response => {
        this.setState({
          users: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  onChangeSearchName(e) {
    const searchName = e.target.value;

    this.setState({
      searchName: searchName
    });
  }

  retrieveUsers() {
    UserDataService.getAll()
      .then(response => {
        this.setState({
          users: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveUsers();
    this.setState({
      currentUser: null,
      currentIndex: -1
    });
  }

  setActiveUser(user, index) {
    this.setState({
      currentUser: user,
      currentIndex: index
    });
  }

  removeAllUsers() {
    UserDataService.deleteAll()
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
  }

// ---------------------------------------------------------
/*
  searchTitle() {
    TutorialDataService.findByTitle(this.state.searchTitle)
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }


  onChangeSearchTitle(e) {
    const searchTitle = e.target.value;

    this.setState({
      searchTitle: searchTitle
    });
  }

  retrieveTutorials() {
    TutorialDataService.getAll()
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveTutorials();
    this.setState({
      currentTutorial: null,
      currentIndex: -1
    });
  }

  setActiveTutorial(tutorial, index) {
    this.setState({
      currentTutorial: tutorial,
      currentIndex: index
    });
  }

  removeAllTutorials() {
    TutorialDataService.deleteAll()
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
  }

  searchTitle() {
    TutorialDataService.findByTitle(this.state.searchTitle)
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }
*/

  render() {
    const { classes } = this.props
    const { searchName, users, currentUser, currentIndex } = this.state;

    return (
      <div className={classes.form}>
        <Grid container>
          <Grid className={classes.search} item sm={12} xs={12} md={12} xl={12} lg={12}>
            <TextField
              label="Search by name"
              value={searchName}
              onChange={this.onChangeSearchName}
            />
            <Button
              size="small"
              variant="outlined"
              className={classes.textField}
              onClick={this.searchName}>
              Search
            </Button>
          </Grid>
          <Grid item md={4}>
            <h2>Users List</h2>

            <div className="list-group">
              {users &&
                users.map((user, index) => (
                  <ListItem
                    selected={index === currentIndex}
                    onClick={() => this.setActiveUser(user, index)}
                    divider
                    button
                    key={index}>
                    {user.firstName + " " + user.lastName}
                  </ListItem>
                ))}
            </div>

            <Button
              className={`${classes.button} ${classes.removeAll}`}
              size="small"
              color="secondary"
              variant="contained"
              onClick={this.removeAllUsers}
            >
              Remove All
          </Button>
          </Grid>
          <Grid item md={8}>
            {currentUser ? (
              <div className={classes.user}>
                <h4>User</h4>
                <div className={classes.detail}>
                  <label>
                    <strong>First Name:</strong>
                  </label>{" "}
                  {currentUser.firstName}
                </div>
                <div className={classes.detail}>
                  <label>
                    <strong>Last Name:</strong>
                  </label>{" "}
                  {currentUser.lastName}
                </div>
                <div className={classes.detail}>
                  <label>
                    <strong>Phone Number:</strong>
                  </label>{" "}
                  {currentUser.phone}
                </div>
                <div className={classes.detail}>
                  <label>
                    <strong>Password:</strong>
                  </label>{" "}
                  {currentUser.password}
                </div>
                <div className={classes.detail}>
                  <label>
                    <strong>Priority Level:</strong>
                  </label>{" "}
                  {currentUser.priorityLevel}
                </div>
                <div className={classes.detail}>
                  <label>
                    <strong>Zone:</strong>
                  </label>{" "}
                  {currentUser.zone}
                </div>
                <div className={classes.detail}>
                  <label>
                    <strong>Status:</strong>
                  </label>{" "}
                  {currentUser.published ? "Published" : "Pending"}
                </div>

                <Link
                  to={"/users/" + currentUser.id}
                  className={classes.edit}
                >
                  Edit
              </Link>
              </div>
            ) : (
                <div>
                  <br />
                  <p className={classes.user}>Please click on a User...</p>
                </div>
              )}
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default withStyles(styles)(UsersList)
