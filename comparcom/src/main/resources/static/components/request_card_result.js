export default {
    template: `
    <div>
    <h2>Header</h2>
    <code class="response-block">
    <p class="code-line" v-for="(header, i) in headerArray" :key="i">
     {{header}}
    </p>
    </code>
    <h2>Body</h2>
    <code class="response-block">{{bodyString}}</code>
    </div>
    `,
    props: {
        headerArray: {
            type: Array,
            default: function () { return [] }
        },
        bodyString: {
            type: String,
            default: ""
        }
    }
}