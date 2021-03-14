//https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI

import React, { Component } from "react";
import UserDataService from "../services/user.service";
import { Link } from "react-router-dom";

import { styles } from "../css-common"
import { TextField, Button, Grid, ListItem, withStyles, Paper } from "@material-ui/core";
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    flexWrap: 'wrap',
    '& > *': {
      margin: theme.spacing(1),
      width: theme.spacing(16),
      height: '100%'
    },
  },
}));

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
        console.log("search found: " + response.data);
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
    //const classes = useStyles();
    const { classes } = this.props
    const { searchName, users, currentUser, currentIndex } = this.state;

    return (
      <div className={classes.root}>
        <Grid container spacing={5}>

          <Grid item className={classes.search} item sm={12}>
            <TextField
              label="Search by name"
              value={searchName}
              onChange={this.onChangeSearchName}
            />
            <Button
              size="small"
              color="primary"
              className={classes.searchBar}
              variant="contained"
              onClick={this.searchName}>
              Search
            </Button>
          </Grid>

          <Grid item sm={4}>
            <Paper Grid container direction="column" justify="center"
            alignItems="center">
              <Grid item><h2>Users List</h2></Grid>
              <div className="listGroup">
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
              {/*
              <Button
                className={`${classes.button} ${classes.removeAll}`}
                size="small"
                color="secondary"
                variant="contained"
                onClick={this.removeAllUsers}
              >Remove All
              </Button>
              */}
            </Paper>
          </Grid>

          <Grid item sm={8}>
            <Paper Grid container direction="column" justify="center"
            alignItems="center" className={classes.listGroup}>
            {currentUser ? (
              <div className={classes.user}>
                <h2>User Details</h2>
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
                  {currentUser.phoneNumber}
                </div>
                <div className={classes.detail}>
                  <label>
                    <strong>Password:</strong>
                  </label>{" "}
                  {currentUser.password}
                </div>
               <div className={classes.detail}>
                  <label>
                    <strong>Zones:</strong>
                  </label>{" "}
                  { currentUser.zones ? currentUser.zones : 'None' }
                </div>
                <div className={classes.detail}>
                  <label>
                    <strong>User Type:</strong>
                  </label>{" "}
                  {currentUser.userType}
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
          </Paper>
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default withStyles(styles)(UsersList)
