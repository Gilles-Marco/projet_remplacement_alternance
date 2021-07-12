<template>
  <div>
    <h2>Payload requêtes</h2>
    <textarea v-model="payload" @input="$emit('update:modelValue', payload)" />
  </div>
</template>

<script>
export default {
  props: {
    modelValue: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      payload: this.modelValue,
    };
  },
};
</script>

<style>
</style>