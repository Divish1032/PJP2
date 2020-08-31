import {app} from './consts';


app.get('/', (req, res) => {
  res.render('index');
});

app.post('/testing-api', (req, res) => {
  res.send("true");
})
