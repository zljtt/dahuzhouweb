import {createStore} from 'vuex'

declare let SessionStorage: any;
const USER = "USER"

const store = createStore({
    state: {
        user: SessionStorage.get(USER) || {},
    },
    getters: {},
    mutations: {
        setUser(state, user) {
            SessionStorage.set(USER, user)
            state.user = user;
        },
        clearUser(state) {
            SessionStorage.remove(USER);
            state.user = {};
        }
    },
    actions: {},
    modules: {}
})

export default store;
