import { Component } from 'react';
import React from 'react';

import logo from './logo.svg';
import './App.css';

class App extends Component {
    constructor() {
        super();

        this.state = {
            countries: [],  // Original data from backend
            comboField: ""  // Remembers combobox selection
        };
    }

    componentDidMount() {
        console.log("Loading state from Backend");
        fetch('http://localhost:8080/')
            .then((response) => response.json())
            .then((countries) =>
                this.setState(
                    () => {
                        return { countries: countries.countries};
                    },
                    () => {
                        console.log(this.state)
                    }
                )
            );
    }

    render() {
      /* keep filtered country list separate so we don't loose data */
      const filteredCountries = this.state.countries.filter((country) => {
        return country.name.includes(this.state.comboField);
      });
      return (
        <div className="App">
            <h1>Hello there!</h1>
            <div>
                {/* Combobox for selecting country (nationality) */}
                <select onChange={(event) => {
                    console.log(event.target.value);
                    this.setState(() => {
                        return { comboField: event.target.value};
                    });
                }}>
                    <option selected value="">Select country</option>
                    { this.state.countries.map( (country) => {
                        return (
                        <option key={country.name} value={country.name}>{country.name}</option>
                        )
                    })}
                </select>
                &nbsp;&nbsp;
                {/* Button for reloading data */}
                <button onClick={(event) => {
                    console.log("Reload data");
                    this.componentDidMount();
                }}>
                Reload Data
                </button>
            </div>
            <div>
                 {/* Render datalist */}
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
