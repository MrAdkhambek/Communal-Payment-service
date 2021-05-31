<template>
  <v-container class="block py-12 w-full md:w-1/2 lg:w-1/4 mx-auto">
    <h1 class="text-3xl font-bold mb-8">Saytga kirish</h1>
    <h3 class="text-lg font-semibold text-gray-500 mb-4">
      Mavjud foydalanuvchilar:
    </h3>
    <div class="mb-4">
      <v-btn color="primary" @click="onSelectAdmin">
        Admin
      </v-btn>
      <v-btn color="primary" @click="onSelectUser">
        User
      </v-btn>
    </div>
    <v-form>
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="username"
            required
            label="Login"
          ></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-text-field
            type="password"
            v-model="password"
            required
            label="Parol"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-btn
        class="bg-green-500 text-white"
        block
        :loading="loading"
        @click="signIn"
      >
        Kirish
      </v-btn>
    </v-form>
  </v-container>
</template>

<script>
export default {
  layout: "empty",
  data() {
    return {
      loading: false,
      username: null,
      password: null
    };
  },
  methods: {
    async signIn() {
      try {
        this.loading = true;
        const { username, password } = this;

        const data = await this.$axios.$post("/user/auth", {
          login: username,
          password
        });

        const { token, ...userData } = data.data;

        this.$store.commit("auth/SET_USER_DATA", userData);
        this.$store.commit("auth/SET_ACCESS_TOKEN", token);
        this.$router.push("/");
        this.loading = false;
      } catch (err) {
        console.error(err);
        this.loading = false;
      }
    },
    onSelectAdmin() {
      this.username = "ADMIN";
      this.password = "ADMIN";
    },
    onSelectUser() {
      this.username = "USER";
      this.password = "USER";
    }
  }
};
</script>

<style></style>
