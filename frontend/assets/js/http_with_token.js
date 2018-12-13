import axios from 'axios'
import router from '../../router'
import store from '../../store'

export const getHttpAuthenticatedFileDownloader = function(theURL) {
    var instance = axios({
        url: theURL,
        method: 'GET',
        headers: {
            'X-Auth-Token': store.state.persistedState.authToken
        },
        responseType: 'blob'
    })
    return instance
}

export const getHttpAuthenticated = function(){
    var instance = axios.create({
        baseURL: '/',
        headers: {
            'X-Auth-Token' : store.state.persistedState.authToken
        }
    })

	instance.interceptors.response.use(
        response =>{
            return response
        },
		error => {
           	if(error.toString().indexOf('401') !== -1) {
				//console.log('Token has expired')
				store.dispatch('setSessionExpired', true)
				router.push({name:'login'})
		  	}
		  	return Promise.reject(error)
	})

	return instance
}


export const getHttpAuthenticatedWithCallBack = function(callback, data){
    if(!callback){
        callback = function(){
            store.dispatch('setLoading', false)
        }
    }

    var instance = axios.create({
        baseURL: '/',
        headers: {
            'X-Auth-Token' : store.state.persistedState.authToken
        }
    })

    instance.interceptors.request.use(
        conf =>{
            return conf
        },
        error => {
            callback(error)
            return Promise.reject(error)
        })

    instance.interceptors.response.use(
        response =>{
            if(!data){
                callback(response.data)
            }else {
                callback(response.data, data)
            }
        },
        error => {
            callback(error)
            if(error.toString().indexOf('401') !== -1) {
                //console.log('Token has expired')
                store.dispatch('setSessionExpired', true)
                router.push({name:'login'})
            }
            return Promise.reject(error)
        })

    return instance
}


export const getHttpAuthenticatedWithLoadingFlag = function(){

    var callback = function(){
        store.dispatch('setLoading', false)
    }

    var instance = axios.create({
        baseURL: '/',
        headers: {
            'X-Auth-Token' : store.state.persistedState.authToken
        }
    })

    instance.interceptors.request.use(
        conf =>{
            store.dispatch('setLoading', true)
            return conf
        },
        error => {
            callback()
            return Promise.reject(error)
        })

    instance.interceptors.response.use(
        response =>{
            callback()
            return response
        },
        error => {
            callback()
            if(error.toString().indexOf('401') !== -1) {
                //console.log('Token has expired')
                store.dispatch('setSessionExpired', true)
                router.push({name:'login'})
            }
            return Promise.reject(error)
        })

    return instance
}
