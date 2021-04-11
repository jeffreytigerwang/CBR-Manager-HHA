import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

/**
 * From https://material-ui.com/components/tables/#BasicTable.js
 */

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
  tableHeader: {
    fontWeight: "bold",
    fontSize: 16,
  },
  tableElements: {
    fontSize: 16,
  },
});

/**
 * 2 column table
 * 
 * @param {[]} data [{id: String, count: int}, headers=[Strings...]]
 * @returns TableContainer
 */
export default function DataTable(data) {
  const classes = useStyles();

    return (
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow>
                {
                  data.headers.map((header, index) => {
                    return (index === 0 ?
                      <TableCell key={header} className={classes.tableHeader}>
                        {header}
                      </TableCell> : 
                      <TableCell key={header} className={classes.tableHeader} align="right">
                        {header}
                      </TableCell>)
                  })
                }
              </TableRow>
            </TableHead>
            <TableBody>
              {data.data.map((row) => (
                <TableRow key={row.id}>
                  <TableCell className={classes.tableElements} component="th" scope="row">
                    {row.id}
                  </TableCell>
                  <TableCell className={classes.tableElements} align="right">
                    {row.count}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
    );
};