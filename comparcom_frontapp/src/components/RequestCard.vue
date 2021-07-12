<template>
  <div class="request-block">
    <h2>{{ title }}</h2>
    <button class="request-button" @click="$emit('send-request')">
      Requête {{ title }}
    </button>
    <slot name="result">
      <request-card-result
        :headers="headers"
        :body="body"
      ></request-card-result>
    </slot>
  </div>
</template>

<script>
import RequestCardResult from "@/components/RequestCardResult";

export default {
  props: {
    title: {
      type: String,
      default: "",
    },
    headers: {
      type: [String, Array, Object],
      default: function () {
        return [];
      },
    },
    body: {
      type: String,
      default: "",
    },
  },
  components: {
    "request-card-result": RequestCardResult,
  },
};
</script>

<style>
</style>