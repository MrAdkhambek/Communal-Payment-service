<template>
  <v-dialog :value="visible" persistent max-width="600px">
    <v-card class="p-4">
      <v-form>
        <h3 class="text-2xl font-semibold">Yangi soha qo'shish</h3>

        <v-container>
          <v-row>
            <v-col cols="12" md="6">
              <v-text-field
                v-model="category.name"
                label="Sohaning nomi"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="6">
              <v-text-field
                label="Rasmni tanlang"
                v-model="category.photo"
                counter
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
      <v-card-actions>
        <v-row>
          <v-col>
            <v-btn block text class="bg-gray-200 " @click="$emit('closed')">
              Yopish
            </v-btn>
          </v-col>
          <v-col>
            <v-btn
              block
              class="bg-green-500 text-white"
              text
              @click="handleCreateCategory"
              :loading="loading"
            >
              Qo'sish
            </v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapActions } from "vuex";

const DEFAULT_CATEGORY_VALUES = {
  name: "",
  photo: null
};

export default {
  props: {
    visible: Boolean
  },
  data() {
    return {
      loading: false,
      category: DEFAULT_CATEGORY_VALUES
    };
  },
  methods: {
    ...mapActions({
      createCategory: "categories/createCategory"
    }),

    async handleCreateCategory() {
      try {
        this.loading = true;
        const { data } = await this.createCategory({ ...this.category });
        this.$toast.success("Soha qo'shildi");

        this.$emit("created", data.data);
      } catch (err) {
        console.error(err);
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style></style>
