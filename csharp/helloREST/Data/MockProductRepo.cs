
using System.Collections.Generic;
using helloREST.Models;

namespace helloREST.Data
{
    public class MockProductRepo : IProductRepo
    {
        public IEnumerable<Product> GetAppProducts()
        {
            var products = new List<Product>
            {
                new Product{
                    Id = 0, name = "Juice", price = 3.2
                },
                new Product{
                    Id = 1, name = "Chocolate", price = 5
                },
                new Product{
                    Id = 2, name = "Beer", price = 6.2
                }
            };

            return products;
        }

        public Product GetProductById(int id)
        {
            return new Product{
                        Id = 0, name = "Juice",  price = 3.2 
            };
        }
    }
}