<template>
  <div>
    <v-card class="mx-auto" max-width="100%" tile>
      <v-list-item-group v-for="service in services" :key="service.id">
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title>{{ service.title }}</v-list-item-title>
          </v-list-item-content>
          <v-list-item-action>
            <v-btn-group>
              <v-btn
                elevation="0"
                class="text-xs  bg-gray-900 text-white font-semibold "
                :to="`/balance/${service.id}`"
              >
                Balansni tekshirish
              </v-btn>
              <v-btn
                elevation="0"
                class="text-xs bg-green-500 text-white font-semibold "
                :to="`/payment/${service.id}`"
              >
                To'lash
              </v-btn>
            </v-btn-group>
          </v-list-item-action>
        </v-list-item>
        <v-divider></v-divider>
      </v-list-item-group>
    </v-card>
    <!-- <v-row>
      <v-col></v-col>
    </v-row> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      services: [
        {
          icon: "",
          id: 1,
          title: "Elektr ta'minoti"
        },
        {
          icon: "",
          id: 2,
          title: "Gaz ta'minoti"
        },
        {
          icon: "",
          id: 3,
          title: "Sovuq suv va oqava suvi"
        },
        {
          icon: "",
          id: 4,
          title: "Issiq suv va isitish tizimi"
        },
        {
          icon: "",
          id: 5,
          title: "Chiqindilarni chiqarish"
        }
      ]
    };
  }
};
</script>

<style></style>
