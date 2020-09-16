using System.Collections.Generic;
using System.Threading.Tasks;
using helloREST.Models;
using MongoDB.Driver;

namespace helloREST.Data
{
    public class ProductMongoRepo : IProductMongoRepo
    {
        MongoDBContext db = new MongoDBContext();
        public async Task Add(ProductMongoDB product)
        {
            try{
                await db.Product.InsertOneAsync(product);
            }catch{
                throw;
            }
        }

        public async Task Delete(string id)
        {
             try{
                FilterDefinition<ProductMongoDB> data = Builders<ProductMongoDB>.Filter.Eq("Id", id);  
                await db.Product.DeleteOneAsync(data);
            }catch{
                throw;
            }
        }

        public async Task<ProductMongoDB> GetProduct(string id)
        {
            try{
                FilterDefinition<ProductMongoDB> filter = Builders<ProductMongoDB>.Filter.Eq("Id", id);  
                return await db.Product.Find(filter).FirstOrDefaultAsync(); 
            }catch{
                throw;
            }
        }

        public async Task<IEnumerable<ProductMongoDB>> GetProducts()
        {
            try{
                return await db.Product.Find(_ => true).ToListAsync();
            }catch{
                throw;
            }
        }

        public async Task Update(ProductMongoDB product)
        {
           try  
            {  
                await db.Product.ReplaceOneAsync(filter: g => g.Id == product.Id, replacement: product);  
            }  
            catch  
            {  
                throw;  
            } 
        }
    }
}