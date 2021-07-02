import RequestConfigurator from './components/request_configurator.js'
import RequestCard from './components/request_card.js'

window.onload = function () {
    let app = Vue.createApp({
        template: `
        <request-configurator v-model="requestConfiguration"/>
        {{requestConfiguration.host_server}}
        {{requestConfiguration.server_resource}}
        {{requestConfiguration.http_method}}
        <section class="request-card">
            <request-card title="AJAX" @send-request="ajax_request" :headerArray="ajax.header" :bodyString="ajax.body">
            </request-card>
            <request-card title="FETCH" @send-request="ajax_request">
            </request-card>
            <request-card title="WEBSOCKET" @send-request="ajax_request">
            </request-card>
            <request-card title="SOCKETIO" @send-request="ajax_request">
            </request-card>
        </section>`
        ,
        components: {
            'request-card': RequestCard,
            'request-configurator': RequestConfigurator
        },
        data () {
            return {
                ajax: {
                    header: [],
                    body: "",
                },
                requestConfiguration: {
                    host_server: "http://localhost:8080",
                    server_resource: "/helloworld",
                    http_method: "GET"
                }
            }
        },
        methods: {
            ajax_request: function () {
                let request = new XMLHttpRequest();
                request.open(this.requestConfiguration.http_method, `${this.requestConfiguration.host_server}${this.requestConfiguration.resource_server}`, true);
                request.send();

                request.onreadystatechange = () => {
                    if (request.readyState === XMLHttpRequest.DONE) {
                        if (request.status === 200) {
                            this.ajax.header = request.getAllResponseHeaders().split('\r\n')
                            this.ajax.body = request.responseText;
                        }
                    }
                }
            },
            fetch_request: function () {

            }
        }
    })
    app.mount("#app")
}