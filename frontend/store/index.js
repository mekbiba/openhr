import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const persistedStateModule = {
	state: {
		lang: ''
	},
	mutations: {
		setLang(state, value){
			state.lang = value
		}
	},
    actions:{
        setLang({commit}, value){
            commit('setLang', value)
        }
    }
}

const store = new Vuex.Store({
	modules: {
        persistedState: persistedStateModule,
	},
	plugins: [createPersistedState({
		storage: window.storage
	})],
    mutations: {
        reset(state, value){
            state.persistedState = persistedStateModule
            state.arvPersistedState = arvPersistedStateModule
        }
    },
    actions: {
        reset({commit}, value) {
            commit('reset', value)
        }
    }
})

export default store
