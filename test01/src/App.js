import logo from './logo.svg';
import './App.css';
import {useEffect} from 'react'
import axios from 'axios'

function App() {
  useEffect(() => {
    let token = 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJVU0VSIn1dLCJlbWFpbCI6InJhbmppdGhAZ21haWwuY29tIiwiaWF0IjoxNzI5MTgwOTMwLCJleHAiOjE3MjkxODQ1MzB9.bkTvrDVCvUqcjKHZ_HDg4aac7qoF3yz8USYc7ZO4ZLM';
    let response = axios.get('https://localhost:8888/user/getByEmail', {headers: {Authorization: `Bearer ${token}`, },})
    console.log(response)
  },[]);
  
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;

