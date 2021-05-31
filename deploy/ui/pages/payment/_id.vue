<template>
  <v-container class="block">
    <div class="mb-8">
      <nuxt-link to="/" class="text-green-500 ">
        <v-icon class="text-green-500">
          mdi-chevron-left
        </v-icon>
        Orqaga
      </nuxt-link>
      <h2 class="text-5xl my-2 font-bold">
        Elektr ta'minoti uchun to'lov
      </h2>
      <p class="text-gray-600 text-lg">
        Siz bu yerda elektr ta'minoti uchun to'lov qilishingiz mumkin
      </p>
    </div>
    <v-row>
      <v-col cols="6">
        <v-form ref="form" lazy-validation>
          <v-text-field
            v-model="cardNumber"
            single-line
            filled
            prepend-inner-icon="mdi-credit-card"
            label="Karta raqami"
            hint="Karta nomerini kiriting 8600 0000 0000 0000"
            persistent-hint
            required
          ></v-text-field>

          <v-text-field
            single-line
            filled
            prepend-inner-icon="mdi-calendar"
            v-model="expiryDate"
            label="Karta muddati"
            hint="Yil/oyni kiriting (16/12)"
            persistent-hint
            required
          ></v-text-field>
        </v-form>
      </v-col>
      <v-col cols="6">
        <v-text-field
          single-line
          filled
          prepend-inner-icon="mdi-user"
          hint="Yil/oyni kiriting (16/12)"
          persistent-hint
          v-model="balanceNumber"
          label="Hisob raqami"
          required
        ></v-text-field>

        <v-text-field
          single-line
          filled
          prepend-inner-icon="mdi-phone"
          hint="Yil/oyni kiriting (16/12)"
          persistent-hint
          v-model="phoneNumber"
          label="Telefon raqami"
          required
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="6">
        <v-autocomplete
          prepend-inner-icon="mdi-map-marker"
          filled
          single-line
          v-model="region"
          :items="regions"
          :filter="filter"
          item-text="name_uz"
          item-value="id"
          placeholder="Hudud"
          label="Hudud"
        ></v-autocomplete>
      </v-col>
      <v-col cols="6">
        <v-autocomplete
          prepend-inner-icon="mdi-map-marker"
          filled
          single-line
          :disabled="!region"
          v-model="district"
          :items="regionDistricts"
          item-text="name_uz"
          item-value="id"
          :filter="filter"
          placeholder="Tuman"
        ></v-autocomplete>
      </v-col>
      <v-col cols="6">
        <v-text-field
          v-model="amount"
          single-line
          filled
          prepend-inner-icon="mdi-cash"
          label="To'lov summasi"
          persistent-hint
          required
        ></v-text-field>
      </v-col>
      <v-col cols="12">
        <v-btn large class="font-bold text-white bg-green-500">
          To'lash
        </v-btn>
        <v-btn large class="font-bold mr-4">
          Tozalash
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      cardNumber: null,
      phoneNumber: null,
      balanceNumber: null,
      expiryDate: null,
      amount: null,
      region: null,
      district: null
    };
  },

  computed: {
    ...mapState({
      regions: state => state.regions,
      districts: state => state.districts
    }),
    regionDistricts() {
      return this.districts.filter(
        ({ region_id }) => region_id === this.region
      );
    }
  },
  methods: {
    filter(item, queryText, itemText) {
      const textOne = item.name_uz.toLowerCase();
      const searchText = queryText.toLowerCase();
      return textOne.indexOf(searchText) > -1;
    }
  }
};
</script>

<style>
.v-input__prepend-inner {
  margin-right: 8px;
}
</style>
