import express from 'express';
import {app} from './consts';
import cookieParser from 'cookie-parser';
import path  from "path";
//here routes defined
import './routes';

// view engine setup
//app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, '../public')));


app.listen(3000, () => {
  console.log('ES6 application listening on port 3000!');
});
