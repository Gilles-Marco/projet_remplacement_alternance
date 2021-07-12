<template>
  <div class="request-config">
    <div>
      <h2>Configurateur de requête</h2>
      <form>
        <div>
          <label for="host-server">URL du serveur</label>
          <input
            v-model="host_server"
            type="text"
            name="host-server"
            placeholder="http://localhost:8080"
            @input="emitObject"
          />
        </div>
        <div>
          <label for="server-resource">Ressource du serveur</label>
          <input
            type="text"
            name="server-resource"
            placeholder="/helloworld"
            v-model="server_resource"
            @input="emitObject"
          />
        </div>
      </form>

      <h3>Spécification pour AJAX / FETCH</h3>

      <form>
        <div>
          <label for="request-method">Méthode utilisée par la requête</label>
          <select
            v-model="http_method"
            name="request-method"
            @change="emitObject"
          >
            <option selected>GET</option>
            <option>POST</option>
            <option>PUT</option>
            <option>PATCH</option>
            <option>DELETE</option>
          </select>
        </div>
      </form>
    </div>

    <div>
      <request-payload v-model="payload" />
    </div>
  </div>
</template>

<script>
import RequestPayload from "@/components/RequestPayload";

export default {
  components: {
    "request-payload": RequestPayload,
  },
  props: {
    modelValue: {
      type: Object,
      default: function () {
        return {
          host_server: "http://localhost:8080",
          server_resource: "/helloworld",
          http_method: "GET",
          payload: "",
        };
      },
    },
  },
  data() {
    return {
      host_server: this.modelValue.host_server,
      server_resource: this.modelValue.server_resource,
      http_method: this.modelValue.http_method,
      payload: this.modelValue.payload,
    };
  },
  methods: {
    emitObject() {
      this.$emit("update:modelValue", {
        host_server: this.host_server,
        server_resource: this.server_resource,
        http_method: this.http_method,
        payload: this.payload,
      });
    },
  },
};
</script>