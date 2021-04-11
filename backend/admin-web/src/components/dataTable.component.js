import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

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

function createData(name, calories, fat, carbs, protein) {
    return { name, calories, fat, carbs, protein };
};

const rows = [
    createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
    createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
    createData('Eclair', 262, 16.0, 24, 6.0),
    createData('Cupcake', 305, 3.7, 67, 4.3),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
];

/**
 * 2 column table
 * 
 * @param {[]} data [{id: String, count: int}, descHeader=String, valueHeader=String]
 * @returns TableContainer
 */
export default function DataTable(data) {
  const classes = useStyles();

    return (
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell className={classes.tableHeader}>{data.descHeader}</TableCell>
                <TableCell className={classes.tableHeader} align="right">{data.valueHeader}</TableCell>
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