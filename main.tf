provider "google" {
  project = "my-project-436711"
  region  = "us-central1"
  credentials = file("./my-project-436711-060922fb417d.json")
}

resource "google_compute_instance" "vm_instance" {
  name         = "example-instance"
  machine_type = "e2-medium"
  zone         = "us-central1-a"

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-11"
    }
  }

  network_interface {
    network = "default"
    access_config {}
  }
}