import { Component } from 'react';

import logo from './logo.svg';
import './App.css';

class App extends Component {
    constructor() {
        super();

        this.state = {
            countries: [],
        };
    }

    componentDidMount() {
        fetch('http://localhost:8080/')
            .then((response) => response.json())
            .then((countries) =>
                this.setState(
                    () => {
                        return { countries: countries.countries };
                    },
                    () => {
                        console.log(this.state)
                    }
                )
            );
    }

    render() {
      return (
        <div className="App">
            <h1>Hello there!</h1>
            { this.state.countries.map((country) => {
                return (
                    <div>
                        <p>{country.name}</p>
                         {country.users.map((user) => {
                            return (
                            <p>{user.name}, {user.gender}, {user.email}</p>
                            )
                         })}
                    </div>
                );
            })}
        </div>
      );
    }
}

export default App;
