const express = require('express');
const chats = require('./chats/chats.json');
const users = require('./users/users.json');
const cors = require('cors');
const jsonGraphqlExpress = require('json-graphql-server/node');


const PORT = 3000;
const app = express();
const data = {
    ...users,
    ...chats
};

app.use(cors());
app.use('/graphql', jsonGraphqlExpress.default(data));
app.listen(PORT);
console.log(`Server started on port http://localhost:${PORT}/graphql`);