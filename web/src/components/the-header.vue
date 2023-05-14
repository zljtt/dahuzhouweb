<template>
  <a-layout-header style="position: fixed; width: 100%; z-index: 9999">
    <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        style="float: right; max-height: 64px; display: flex"
    >
      <a-menu-item key="home" style="display: flex; align-items: center; justify-content: center;">
        <router-link to="/home">
          <span style="font-size: 1.5em">大湖洲</span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="wiki">
        <router-link to="/home">
          WIKI
        </router-link>
      </a-menu-item>
      <a-menu-item v-if="!('username' in loginUser) ||  loginUser.username === ''" key="login" @click="openLogin()">
        登录
        <a-modal v-model:visible="visible" title="Title" @ok="login">
          <a-form :model="formState" ref="formRef">
            <a-form-item label="用户名" name="username">
              <a-input v-model:value="formState.username"/>
            </a-form-item>
          </a-form>
          <a-form-item label="密码" name="password">
            <a-input-password v-model:value="formState.password"/>
          </a-form-item>
          <template #footer>
            <a-form :model="formState" ref="formRef">
              <a-form-item>
                <a-button type="primary" html-type="submit" @click="login">登录</a-button>
              </a-form-item>
            </a-form>
          </template>
        </a-modal>
      </a-menu-item>
      <template v-else>
        <a-menu-item key="username">
          {{ loginUser.username }}
        </a-menu-item>
        <a-menu-item key="editor">
          <router-link to="/editor">
            编辑器
          </router-link>
        </a-menu-item>
      </template>
      <a-menu-item key="about">
        <router-link to="/about">
          关于我们
        </router-link>
      </a-menu-item>
      <a-menu-item key="contact">
        <router-link to="/home">
          联系我们
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script lang="ts">
import {computed, defineComponent, onMounted, reactive, ref} from 'vue';
import axios from "axios";
import {message} from "ant-design-vue";
import store from '@/store';

interface FormState {
  username: string;
  password: string;
}

export default defineComponent({
  name: 'user-header',
  props: {
    selected: {
      type: String,
      required: true,
      default: "home"
    }
  },
  setup(props) {
    console.log("setup");
    const visible = ref<boolean>(false);
    const login = () => {
      axios.post("/user/login", formState.value).then((result) => {
        message.success({content: result.data.message})
        if (result.data.success) {
          store.commit("setUser", {
            username: result.data.username,
            token: result.data.token
          });
          visible.value = false;
        }
      });
    }
    const loginUser = computed(() => store.state.user);
    const formState = ref({
      username: '',
      password: '',
    });
    const openLogin = () => {
      visible.value = true;
    }
    return {
      visible,
      login,
      formState,
      openLogin,
      loginUser,
      selectedKeys: ref<string[]>([props.selected]),
    }
  }
});
</script>