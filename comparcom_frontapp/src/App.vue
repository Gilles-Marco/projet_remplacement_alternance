<template>
  <div>
    <request-configurator v-model="requestConfiguration" />

    <section class="request-card">
      <request-card
        title="AJAX"
        @send-request="ajax_request"
        :headerArray="ajax.header"
        :bodyString="ajax.body"
      >
      </request-card>
      <request-card title="FETCH" @send-request="ajax_request"> </request-card>
      <request-card title="WEBSOCKET" @send-request="ajax_request">
      </request-card>
      <request-card title="SOCKETIO" @send-request="ajax_request">
      </request-card>
    </section>
  </div>
</template>

<script>
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
        header: [],
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
      console.log(this.url);
      let request = new XMLHttpRequest();
      request.open(this.requestConfiguration.http_method, this.url, true);
      request.send();

      request.onreadystatechange = () => {
        if (request.readyState === XMLHttpRequest.DONE) {
          if (request.status === 200) {
            this.ajax.header = request.getAllResponseHeaders().split("\r\n");
            this.ajax.body = request.responseText;
          }
        }
      };
    },
    fetch_request: function () {},
  },
};
</script>