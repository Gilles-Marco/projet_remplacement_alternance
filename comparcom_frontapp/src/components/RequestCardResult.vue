<template>
  <div>
    <h2>Header</h2>
    <code class="response-block">
      <p class="code-line" v-for="(header, i) in headers" :key="i">
        {{ header }}
      </p>
    </code>
    <h2>Body</h2>
    <code class="response-block">{{ body }}</code>
  </div>
</template>

<script>
export default {
  props: {
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
};
</script>

<style>
</style>