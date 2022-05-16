import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import {Provider} from "react-redux"
import { stateProto } from './redux/state';
import {legacy_createStore as createStore} from 'redux'
import reducer from './redux/reducer';



const root = ReactDOM.createRoot(document.getElementById('root'));
const store = createStore(reducer, stateProto, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());
root.render(
  <React.StrictMode>
    <Provider store = {store}>

      <App />
    </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
