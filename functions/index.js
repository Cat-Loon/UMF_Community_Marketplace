const functions = require('firebase-functions');
const request = require('request-promise')

exports.indexPostsToElastic = functions.database.ref('/book_listings/{TheAuthor}')
	.onWrite((change,context) => {
		let postData = change.after.val();
		let TheAuthor = context.params.TheAuthor;
		
		console.log('Indexing book_listings:', postData);
		
		let elasticSearchConfig = functions.config().elasticsearch;
		let elasticSearchURL = elasticSearchConfig.url + 'book_listings/listing/' + TheAuthor;
		let elasticSearchMethod = postData ? 'POST' : 'DELETE';
		
		let elasticSearchRequest = {
			method: elasticSearchMethod,
			url: elasticSearchURL,
			auth:{
				username: elasticSearchConfig.username,
				password: elasticSearchConfig.password,
			},
			body: postData,
			json: true	
		};
		
		return request(elasticSearchRequest).then(response => {
			console.log("ElasticSearch response", response);
			return null;
		});
		
	});

