<template>
  <div>
    <request-configurator v-model="requestConfiguration" />

    <section class="request-card">
      <request-card
        title="AJAX"
        @send-request="ajax_request"
        :headers="ajax.headers"
        :body="ajax.body"
      >
      </request-card>
      <request-card
        title="FETCH"
        @send-request="fetch_request"
        :headers="fetch.headers"
        :body="fetch.body"
      >
      </request-card>
      <request-card
        title="WEBSOCKET"
        @send-request="websocket_request"
        :headers="websocket.headers"
        :body="websocket.body"
      >
      </request-card>
      <request-card
        title="SOCKETIO"
        @send-request="socketio_request"
        :headers="socketio.headers"
        :body="socketio.body"
      >
      </request-card>
    </section>
  </div>
</template>

<script>
import * as SockJS from "sockjs-client";
import * as Stomp from "stomp-websocket";
import { connect } from "socket.io-client";

import RequestCard from "@/components/RequestCard";
import RequestConfigurator from "@/components/RequestConfigurator";

export default {
  components: {
    "request-card": RequestCard,
    "request-configurator": RequestConfigurator,
  },
  data() {
    return {
      ajax: {
        headers: [],
        body: "",
      },
      fetch: {
        headers: [],
        body: "",
      },
      websocket: {
        headers: [],
        body: "",
      },
      socketio: {
        headers: [],
        body: "",
      },
      requestConfiguration: {
        host_server: "http://localhost:8080",
        server_resource: "/helloworld",
        http_method: "GET",
        payload: "",
      },
    };
  },
  computed: {
    url() {
      return `${this.requestConfiguration.host_server}${this.requestConfiguration.server_resource}`;
    },
  },
  methods: {
    ajax_request: function () {
      let request = new XMLHttpRequest();
      request.open(this.requestConfiguration.http_method, this.url, true);
      request.send();

      request.onreadystatechange = () => {
        if (request.readyState === XMLHttpRequest.DONE) {
          if (request.status === 200) {
            this.ajax.headers = request.getAllResponseHeaders().split("\r\n");
            this.ajax.body = request.responseText;
          }
        }
      };
    },
    fetch_request: function () {
      fetch(this.url).then((response) => {
        if (response.ok) {
          this.fetch.headers = response.headers;
          response.blob().then((blob) => {
            blob.text().then((text) => {
              this.fetch.body = text;
            });
          });
        }
      });
    },
    websocket_request: function () {
      let socket = new SockJS("http://localhost:8080/websocket");
      let stompClient = Stomp.over(socket);
      stompClient.debug = null;
      stompClient.connect({}, (frame) => {
        stompClient.subscribe("/helloJSWS", (message) => {
          let body = message.body;
          delete message.body;
          this.websocket.headers = [message.headers];
          this.websocket.body = body;
          socket.close();
        });

        stompClient.send(
          "/helloSpringWS",
          {},
          "Javascript say hello to Spring Websocket"
        );
      });
    },
    socketio_request: function () {
      let sio = connect("http://localhost:8082/socketio");

      sio.on("connect", () => {
        sio.emit("helloSpringSocketIO", "Hello Spring SocketIO !");
      });

      sio.on("helloJSSocketIO", (message) => {
        this.socketio.body = message;
        sio.close();
      });
    },
  },
};
</script>