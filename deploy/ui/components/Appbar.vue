<template>
  <v-container class="block relative">
    <v-app-bar absolute color="white" elevation="0">
      <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->

      <v-toolbar-title>
        <nuxt-link to="/" class="text-black">
          <strong>Kommunal</strong>Xizmatlar
        </nuxt-link>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn
        v-if="!isAuthenticated"
        color="successs"
        class="bg-green-500 text-white"
        to="/login"
      >
        <v-icon class="mr-2">
          mdi-login-variant
        </v-icon>
        Kirish
      </v-btn>

      <v-menu v-else offset-y bottom left>
        <template v-slot:activator="{ on, attrs }">
          <v-btn dark icon v-bind="attrs" v-on="on">
            <v-avatar color="primary" size="40">
              <v-icon dark>
                mdi-account-circle
              </v-icon>
            </v-avatar>
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            link
            v-for="(item, i) in navItems"
            :key="i"
            :to="item.to"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    navItems() {
      return [
        { title: "Shaxsiy kabinet", to: "/profile" },
        { title: "Chiqish", to: "/logout" }
      ];
    },

    ...mapGetters({
      isAuthenticated: "auth/isAuthenticated"
    })
  }
};
</script>

<style></style>
