import { Component } from 'react';

import logo from './logo.svg';
import './App.css';

class App extends Component {
    constructor() {
        super();

        this.state = {
            countries: [],
            comboField: ""
        };
    }

    componentDidMount() {
        console.log("Loading state from Backend");
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
      const filteredCountries = this.state.countries.filter((country) => {
        return country.name.includes(this.state.comboField);
      });
      return (
        <div className="App">
            <h1>Hello there!</h1>
            <div>
                <select onChange={(event) => {
                    console.log(event.target.value);
                    this.setState(() => {
                        return { comboField: event.target.value};
                    });
                }}>
                    { this.state.countries.map( (country) => {
                        return (
                        <option key={country.name} value={country.name}>{country.name}</option>
                        )
                    })}
                </select>
                &nbsp;&nbsp;
                <button onClick={(event) => {
                    console.log("Reload data");
                    this.componentDidMount();
                }}>
                Reload Data
                </button>
            </div>
            <div>
                // Render datalist
                { filteredCountries.map((country) => {
                    return (
                        <div>
                             {country.users.map((user) => {
                                return (
                                <p>{country.name}, {user.name}, {user.gender}, {user.email}</p>
                                )
                             })}
                        </div>
                    );
                })}
            </div>
        </div>
      );
    }
}

export default App;
