import React, { Component } from 'react';
import './App.css';
class App extends Component
{
  constructor()
  {
    super();
    this.state={
      userName:null,
      password:null,
      login:false,
      store:null,
      baseAmmount:null,
      baseCurrency:null,
      targetCurrency:null,
      sourceAccountNumber:null,
      targetAccountNumber:null,
      token:null,
      paymentId:null,
      isLoaded:false,
      isPayment:false,
      result1:null,
      result2:null,
      result3:null,
      result4:null,
      result5:null,
      result6:null,
      result7:null,
      result8:null,
      result9:null,
      result10:null
    }
  }

  componentDidMount()
  {
    this.storeCollector()
  }

  storeCollector()
  {
    let store = JSON.parse(localStorage.getItem('login'));
    if(store && store.login && store.token != null)
    {
      this.setState({login:true, store:store, userName:null, password:null})
    }
  }

  login()
  {
    fetch('http://localhost:9192/authenticate',{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body: JSON.stringify({userName:this.state.userName, password:this.state.password})
    }).then((response) => {
      response.json().then((result) => {
        console.warn("result", result);
        //this.setState({token:result.jwt})
        //console.log(result.jwt)
        localStorage.setItem('login', JSON.stringify({
          login:true,
          token:result.jwt
        }))
        //console.log(localStorage.getItem('login'))
        
          this.storeCollector()
        
      })
    })
  }

  post()
  {
    let token = "Bearer " + this.state.store.token
    console.log(localStorage.getItem('login'))
    fetch('http://localhost:9192/localPaymentRequest',{
      method:"POST",
      headers:{"Content-Type":"application/json",
      "Authorization":token},
      body: JSON.stringify({baseAmmount:this.state.baseAmmount,
                            baseCurrency:this.state.baseCurrency,
                            targetCurrency:this.state.targetCurrency,
                            sourceAccountNumber:this.state.sourceAccountNumber,
                            targetAccountNumber:this.state.targetAccountNumber
                          })
    }).then((response) => {
      response.json().then((result) => {
        console.warn("result", result);
        this.setState({isPayment:true,
          result1:result.baseAmmount,
          result2:result.baseCurrency,
          result3:result.customerId,
          result4:result.paymentStatus,
          result5:result.sourceAccountNumber,
          result6:result.targetAccountNumber,
          result7:result.targetAmmount,
          result8:result.targetCurrency,
          result9:result.timeStamp,
          result10:result.transactionReferenceNumber})
      })
    })
  }

  post2()
  {
    let token = "Bearer " + this.state.store.token
    console.log(localStorage.getItem('login'))
    fetch('http://localhost:9192/crossPaymentRequest',{
      method:"POST",
      headers:{"Content-Type":"application/json",
      "Authorization":token},
      body: JSON.stringify({baseAmmount:this.state.baseAmmount,
                            baseCurrency:this.state.baseCurrency,
                            targetCurrency:this.state.targetCurrency,
                            sourceAccountNumber:this.state.sourceAccountNumber,
                            targetAccountNumber:this.state.targetAccountNumber
                          })
    }).then((response) => {
      response.json().then((result) => {
        console.warn("result", result);
        this.setState({isPayment:true,
          result1:result.baseAmmount,
          result2:result.baseCurrency,
          result3:result.customerId,
          result4:result.paymentStatus,
          result5:result.sourceAccountNumber,
          result6:result.targetAccountNumber,
          result7:result.targetAmmount,
          result8:result.targetCurrency,
          result9:result.timeStamp,
          result10:result.transactionReferenceNumber})
      })
    })
  }

  get()
  {
    let token = "Bearer " + this.state.store.token
    let ur = "http://localhost:9192/response/" + this.state.paymentId
    fetch(ur, {
      method:"GET",
      headers:{"Content-Type":"application/json",
      "Authorization":token}
    }).then((response) => {
      response.json().then((result) => {
        console.warn("result", result);
        this.setState({isLoaded:true,
          result1:result.baseAmmount,
          result2:result.baseCurrency,
          result3:result.customerId,
          result4:result.paymentStatus,
          result5:result.sourceAccountNumber,
          result6:result.targetAccountNumber,
          result7:result.targetAmmount,
          result8:result.targetCurrency,
          result9:result.timeStamp,
          result10:result.transactionReferenceNumber})
      })
    })
  }

  logout()
  {
    localStorage.clear()
    this.setState({login:false})
  }
  
  render()
  {
    if(this.state.isLoaded)
    {
      return(
        <div>
          <table id = "tbl1">
            <tr>
              <th>Payment Components</th>
              <th>Payment Details</th>
            </tr>
            <tr>
              <td>Customer ID</td>
              <td>{this.state.result3}</td>
            </tr>
            <tr>
              <td>Base Ammount</td>
              <td>{this.state.result1}</td>
            </tr>
            <tr>
              <td>Customer IDBase Currency</td>
              <td>{this.state.result2}</td>
            </tr>
            <tr>
              <td>Payment Status</td>
              <td>{this.state.result4}</td>
            </tr>
            <tr>
              <td>Source AccountNumber</td>
              <td>{this.state.result5}</td>
            </tr>
            <tr>
              <td>Target AccountNumber</td>
              <td>{this.state.result6}</td>
            </tr>
            <tr>
              <td>Target Ammount</td>
              <td>{this.state.result7}</td>
            </tr>
            <tr>
              <td>Target Currency</td>
              <td>{this.state.result8}</td>
            </tr>
            <tr>
              <td>Time Stamp</td>
              <td>{this.state.result9}</td>
            </tr>
            <tr>
              <td>Transaction Reference Number</td>
              <td>{this.state.result10}</td>
            </tr>
          </table>
        </div>
      );
    }
    if(this.state.isPayment)
    {
      return(
        <div>
          <table id = "tbl2">
            <tr>
              <th>Payment Components</th>
              <th>Payment Details</th>
            </tr>
            <tr>
              <td>Customer ID</td>
              <td>{this.state.result3}</td>
            </tr>
            <tr>
              <td>Base Ammount</td>
              <td>{this.state.result1}</td>
            </tr>
            <tr>
              <td>Customer IDBase Currency</td>
              <td>{this.state.result2}</td>
            </tr>
            <tr>
              <td>Payment Status</td>
              <td>{this.state.result4}</td>
            </tr>
            <tr>
              <td>Source AccountNumber</td>
              <td>{this.state.result5}</td>
            </tr>
            <tr>
              <td>Target AccountNumber</td>
              <td>{this.state.result6}</td>
            </tr>
            <tr>
              <td>Target Ammount</td>
              <td>{this.state.result7}</td>
            </tr>
            <tr>
              <td>Target Currency</td>
              <td>{this.state.result8}</td>
            </tr>
            <tr>
              <td>Time Stamp</td>
              <td>{this.state.result9}</td>
            </tr>
            <tr>
              <td>Transaction Reference Number</td>
              <td>{this.state.result10}</td>
            </tr>
          </table>
        </div>
      );
    }
    return (
      <div>
        <h1>ABC BANK</h1>
        {
          !this.state.login?
          <div class = "login">
            <label for="uname">User Name:</label><br></br>
            <input type="text" onChange={(event) => {this.setState({userName:event.target.value})}} /> <br></br>
            <label for="pwd">Password:</label><br></br>
            <input type="password" onChange={(event) => {this.setState({password:event.target.value})}} /> <br></br>
            <button onClick={() => {this.login()}}> Login </button>
        </div>
        :
        <div >
          <h2 id="welcomeHeader">Welcome to ABC Payment Gateway</h2>
          <div class="paymentGateway">
          <div class = "local">
          <h3>Local Payment</h3>
          <label for="bam">Base Ammount:</label><br></br>
          <input type="text" onChange={(event) => {this.setState({baseAmmount:event.target.value})}} /> <br></br>
          <label for="bam">Base Currency:</label><br></br>
          <input type="text" onChange={(event) => {this.setState({baseCurrency:event.target.value})}} /> <br></br>
          <label for="bam">Source Account Number:</label><br></br>
          <input type="text" onChange={(event) => {this.setState({sourceAccountNumber:event.target.value})}} /> <br></br>
          <label for="bam">Target Account Number:</label><br></br>
          <input type="text" onChange={(event) => {this.setState({targetAccountNumber:event.target.value})}} /> <br></br>
          <button onClick = {() => {this.post()}}> Make Payment </button>
          {
            this.state.response
          }
          </div>
          <div class = "cross">
          <h3>Cross Payment</h3>
          <label for="bam">Base Ammount:</label><br></br>
          <input type="text" onChange={(event) => {this.setState({baseAmmount:event.target.value})}} /> <br></br>
          <label for="bam">Base Currency:</label><br></br>
          <input type="text" onChange={(event) => {this.setState({baseCurrency:event.target.value})}} /> <br></br>
          <label for="bam">Target Currency:</label><br></br>
          <input type="text" onChange={(event) => {this.setState({targetCurrency:event.target.value})}} /> <br></br>
          <label for="bam">Source Account Number:</label><br></br>
          <input type="text" onChange={(event) => {this.setState({sourceAccountNumber:event.target.value})}} /> <br></br>
          <label for="bam">Target Account Number:</label><br></br>
          <input type="text" onChange={(event) => {this.setState({targetAccountNumber:event.target.value})}} /> <br></br>
          <button onClick = {() => {this.post2()}}> Make Payment </button>
          {
            this.state.response
          }
          </div>
          <div class = "status">
          <h3>Payment Status</h3>
          <label for="bam">Payment Id Number:</label>
          <input type="text" onChange={(event) => {this.setState({paymentId:event.target.value})}} /> <br></br>
          <button onClick = {() => {this.get()}}> Get Payment Details </button><br></br>
          {
            this.state.response
          }
          </div>
          </div>
          <button id="btn3" onClick = {() => {this.logout()}}> Logout </button>
        </div>
        }
      </div>
    );
  }
}
export default App;
