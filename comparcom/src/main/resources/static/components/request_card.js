import RequestCardResult from './request_card_result.js'

export default {
    template: `
    <div class="request-block">
    <h2>{{title}}</h2>
    <button class="request-button" @click="$emit('send-request')">Requête ajax</button>
    <slot name="result">
        <request-card-result :headerArray="headerArray" :bodyString="bodyString"></request-card-result>
    </slot>
    </div>`,
    props: {
        title: {
            type: String,
            default: ""
        },
        headerArray: {
            type: Array,
            default: function () { return [] }
        },
        bodyString: {
            type: String,
            default: ''
        }
    },
    components: {
        'request-card-result': RequestCardResult
    },
}