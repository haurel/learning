using System.Collections.Generic;
using System.Threading.Tasks;
using helloREST.Models;

namespace helloREST.Data
{
    public interface IProductMongoRepo
    {
         Task Add(ProductMongoDB product);
         Task Update(ProductMongoDB product);
         Task Delete(string id);
         Task<ProductMongoDB> GetProduct(string id);
         Task<IEnumerable<ProductMongoDB>> GetProducts();

    }
}