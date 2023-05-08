<template>
  <a-collapse v-model:activeKey="activeKey">
    <a-collapse-panel key="1" v-if="userData" :header="userData.content.name" :show-arrow="false"
                      style="-webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none;">
      <p>{{ userData.name }}</p>
    </a-collapse-panel>
  </a-collapse>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from "axios";

export default defineComponent({
  name: 'user-header',
  setup() {
    console.log("setup");
    const activeKey = ref(['1']);
    const userData = ref();

    onMounted(() => {
      axios.get("/user?name=zljtt").then(response => {
        userData.value = response.data;
        console.log(response.data)
      });
    })
    return {
      activeKey,
      userData
    }
  }
});
</script>