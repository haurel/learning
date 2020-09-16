using System.ComponentModel.DataAnnotations;

namespace helloREST.Models
{
    public class Product
    {
        public int Id{ get; set; }

        [Required]
        [StringLengthAttribute(60, MinimumLength=3)]
        public string name { get; set; }

        public double price { get; set; }
    }
}