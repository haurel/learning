using System.Collections.Generic;
using helloREST.Models;

namespace helloREST.Data
{
    public interface IProductRepo
    {
         IEnumerable<Product> GetAppProducts();
         Product GetProductById(int id);
         
    }
}