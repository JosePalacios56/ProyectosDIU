import React, {Component} from 'react';

class notification extends Component{

constructor(){

    super()
    this.state={
    eventSource: new EventSource("http://localhost:8081/tutorials/new_notification")
    }
}

    componentDidMount (){
        this.state.eventSource.onmessage= function(e){
        let notification = JSON.parse(e.data);
            document.getElementById("notificationDiv").innerHTML += notification.text + "at " + new Date(notification.time)
        }
    }

    render() {
        return (
            <div id="notificationDiv"></div>
        )
    }
}
export default notification