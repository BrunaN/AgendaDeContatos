var app = new Vue({

    el: '#app',

    data: {
        name: '',
        email: '',
        number: '',

        submitted: false,

        contacts: []
    },

    methods:{

        submit : function(event){
            //alert(this.name)
            this.$http.post('http://localhost:8080/contacts', {
                "name" : this.name,
                "email": this.email,
                "number": this.number
            }).then(function(res){
                this.contacts.push(res.body);

                this.contacts.sort(function (a, b) {
                    if (a.name > b.name) {
                        return 1;
                    }
                    if (a.name < b.name) {
                        return -1;
                    }
                    // a must be equal to b
                    return 0;
                });

                this.submitted = true;

                this.name = ""
                this.email = ""
                this.number = ""

            })
        },

        remove : function (contact) {
            this.$http.delete('http://localhost:8080/contact' + "/"+ contact.id).then(function(data){

                for(var i = 0; i < this.contacts.length; i++){
                    if(this.contacts[i].id == contact.id){
                        this.contacts.splice( i, 1);
                    }
                }

                console.log(data);
            })
        },

        save : function (contact){
            this.$http.put('http://localhost:8080/contact' + "/"+ contact.id, contact).then(function(res){
                this.$parent.cancel();
            })
        }

    },

    created() {
        this.$http.get('http://localhost:8080/contacts').then(function(data){
            this.contacts = data.body;

            this.contacts.sort(function (a, b) {
                if (a.name > b.name) {
                    return 1;
                }
                if (a.name < b.name) {
                    return -1;
                }
                // a must be equal to b
                return 0;
            });

            console.log(this.contacts);
        })

    }

});

Vue.component('contact-component', {

    props: ['contact'],

    data: function () {
        return {
            editted: false
        }
    },

    template: `
                <div class="contact">
                    <div v-if="!editted" class="infos">
                        <h3>
                            {{ contact.name }}
                        </h3>
                        <p>
                            {{ contact.email }}
                        </p>
                        <h3>
                            {{ contact.number }}
                        </h3>
                    </div>

                    <div v-if="editted" class="edit">
                        <form>
                            <input v-model="contact.name" type="text"/>
                            <input v-model="contact.email" type="text"/>
                            <input v-model="contact.number" type="text"/>
                        </form>
                    </div>

                    <div v-if="!editted" class="icons">
                        <button v-on:click="$emit('contact-remove', contact)">
                            <i class="far fa-trash-alt"></i>
                        </button>
                        <button v-on:click="edit">
                            <i class="far fa-edit"></i>
                        </button>
                    </div>

                    <div v-if="editted" class="icons-edit">
                        <button v-on:click="$emit('contact-save', contact)">
                            Alterar contato
                        </button>
                        <button v-on:click="cancel">
                            Fechar
                        </button>
                    </div>


                </div>
            `,

    methods: {

        edit : function () {
            this.editted = true;
        },

        cancel : function () {
            this.editted = false;
        }

    }

});